cd src/

echo -e "Compiling Java files..."

# Recompile Java files
javac otbnb/*.java

declare -a sessions=("../test/delete/input/R1T1.txt" "../test/create/input/R1T2.txt" "../test/create/input/R3T1.txt" "../test/rent/input/R4T1.txt" "../test/search/input/R3T3.txt")

# Remove combined daily transaction file
FILE="../daily/combinedDailyTransactionFile.txt" 
if [ -f $FILE ]; then        
    rm "../daily/combinedDailyTransactionFile.txt"
fi

# Loop 5 times to run all 
for i in {1..5}
do
    echo -e "\n--- Running session $i, ${sessions[$i-1]} ---"

    # Run the front end, saving to the relevant daily transaction file
    java otbnb.Server "../userdemo.txt" "../listingsdemo.txt" "../daily/dailyTransactionFile-$i.txt" < "${sessions[$i-1]}"

    # Append daily transaction file from current session to combined file
    cat "../daily/dailyTransactionFile-$i.txt" >> "../daily/combinedDailyTransactionFile.txt" 

done

# Run back-end
echo -e "\nRunning back-end..."

cd ../../TMT-OTBNB-BackEnd/src

# Compile back-end file
javac BackEnd.java
java BackEnd ../../TMT-OTBNB/userdemo.txt ../../TMT-OTBNB/listingsdemo.txt ../../TMT-OTBNB/daily/combinedDailyTransactionFile.txt