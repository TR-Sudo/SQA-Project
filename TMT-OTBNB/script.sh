cd src/

# All transactions that are being tested
declare -a transactions=("login" "logout" "post" "create" "delete" "rent" "search")

# Recompile Java files
javac otbnb/*.java

# Loop through all transactions one-by-one
for transaction in "${transactions[@]}"
do
   echo -e "\n--- Running $transaction tests ---"

    # Find all the test input files with .txt extension, sort them
    # Sorting ensures R1T1 runs before R1T2, etc...
    for i in $(find ../test/${transaction}/input -name "*.txt" | sort -n); do
        # Chop off the relative directory part of the path, we just want the test id, e.g., R1T1
        base=${i##*/}
        testid=${base%.*}

        # Execute the test, using current test input as input and redirecting output to applicable output folder
        echo "running test ${testid}"
        java otbnb.Server "../userdemo.txt" "../listingsdemo.txt" "../dailyTransactionFile.txt" < $i > "../test/${transaction}/output/${testid}.txt"

        # Compare expected console output
        if diff --strip-trailing-cr "../test/${transaction}/output/${testid}.txt" "../test/${transaction}/expectedOutput/${testid}.txt"; then
            echo "  CO Passed $testid";
        else
            echo "  CO Failed $testid";
        fi

        # Compare expected daily transaction file if it exists
        FILE="../test/${transaction}/expectedOutput/${testid}_dtfile.txt" 
        if [ -f $FILE ]; then
            # if post transaction don't compare rental id because this is randomized, it won't match!
            if [ $transaction == "post" ]; then
                # Remove rental id and then compare chars 1-22 and 31 onwards
                if diff --strip-trailing-cr <(cut -c1-22,31- "../dailyTransactionFile.txt") <(cut -c1-22,31- "../test/${transaction}/expectedOutput/${testid}_dtfile.txt"); then
                    echo "  TO Passed $testid";
                else
                    echo "  TO Failed $testid";
                fi
            else
                # Non post transaction compares whole file 
                if diff --strip-trailing-cr "../dailyTransactionFile.txt" "../test/${transaction}/expectedOutput/${testid}_dtfile.txt"; then
                    echo "  TO Passed $testid";
                else
                    echo "  TO Failed $testid";
                fi
            fi
        fi
    done
done