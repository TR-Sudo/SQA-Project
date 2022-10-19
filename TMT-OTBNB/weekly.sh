# Loop 5 times to represent a week
for i in {1..5}
do
    echo -e "\n--- Running daily session $i ---"

    path=`pwd`

    "$path"/daily.sh
done