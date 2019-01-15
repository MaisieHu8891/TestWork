#
i=0
j=1000
n="b1532adc-720f-4297-9b8e-e0687e25f841"
while (( i< 5000 ))
 do
   let "i+=1"
   let "j+=1"
echo ${n}|sed "s/-[0-9]\{4\}/-$j/">>abc1.txt
done
