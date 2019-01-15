getZanNumber()
{
	tn=$1
	#要看第几个精华帖
	rn=$2
	#要查这个精华帖中第几个回复的数量
	allarray=()
	jhrparray=()
	i=0
	j=0
	gtotal=`curl -s https://testerhome.com/topics/excellent 2>/dev/null|sed -n '/panel-body item-list/,/panel-footer clearfix/p'|grep -c "topic media topic-"`
	glist=`curl -s https://testerhome.com/topics/excellent 2>/dev/null|sed -n '/panel-body item-list/,/panel-footer clearfix/p'|grep "topic media topic-"|grep -o "[0-9]*" > jhlist`
	echo "精华帖共有${gtotal}个"
	if [ $((10#${tn})) -gt $((10#${gtotal})) ]
	then
		echo "输入的精华帖超出数量范围"
		return 0
	fi
	while read id
	do
		allarray[${i}]=${id};
		((i++));
	done < jhlist
	jhid=${allarray[$((tn-1))]}
	jhrpnumber=`curl -s https://testerhome.com/topics/${jhid} 2>/dev/null|grep "共收到.*"|grep -o "[0-9]*"|head -1`
	echo "id为${jhid}共有${jhrpnumber}个回复"
	jhrp=`curl -s https://testerhome.com/topics/${jhid} 2>/dev/null|grep 'data-type="Reply"'|awk -F "\"" '{print $15}' > rplist`
	while read zn
	do 
		jhrparray[${j}]=${zn};
		(( j++ ));
	done < rplist
	rp=${jhrparray[$(( rn-1 ))]}
	jhrpnum=`echo ${rp}|grep -o "[0-9]*"`
	if [ ${jhrpnum}x == ""x ]
	then
		rpn=0
	else
		rpn=$jhrpnum
	fi

	echo "第${rn}条回复的点赞数是${rpn}"
}

getZanNumber 2 1
