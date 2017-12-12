#! /bin/bash
#author PJJ
#date 2017/11/27
#function split catalina.log by keys and return the result we need
echo "测试信息！"
#get name of script
shellName=`basename $0`
if [[ $# -eq 0 ]]; then
	#args is 0 return
	echo "Useage:$shellName resId|orderId|time fileName"
	exit 1
fi

if [[ $# -eq 1 ]]; then
	#args is 1 return
	echo "Useage:$shellName resId|orderId|time fileName, fileName not be null"
	exit 1
fi

#define params we need
resId=""
orderId=""
now=""
fileName=""

if [[ $# -eq 2 ]]; then
	if [[ ! $2 =~ \/ ]]; then
		#statements
		echo "Useage:$shellName resId|orderId|time fileName, fileName must be last param"
		exit 1
	fi
	fileName=$2
	var=$1
	echo ${#var} | egrep "^8" >>/dev/null && resId=$1
	echo ${#var} | egrep "11" >>/dev/null && resId=$1
	echo ${#var} | egrep "18" >>/dev/null && orderId=$1
	echo $1 | egrep "[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}">>/dev/null && now=$1
fi

if [[ $# -eq 3 ]]; then
	if [[ ! $3 =~ \/ ]]; then
		#statements
		echo "Useage:$shellName resId|orderId|time fileName, fileName must be last param"
		exit 1
	fi
	fileName=$3
	for i in $@; do
		#statements
		if [[ ! i =~ \/ ]]; then
			#statements
			echo ${#i} | egrep "^8" >>/dev/null && resId=$i
			echo ${#i} | egrep "11" >>/dev/null && resId=$i
			echo ${#i} | egrep "18" >>/dev/null && orderId=$i
			echo $i | egrep "[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}">>/dev/null && now=$i
		fi
	done
fi

if [[ $# -eq 4 ]]; then
	if [[ ! $4 =~ \/ ]]; then
		#statements
		echo "Useage:$shellName resId|orderId|time fileName, fileName must be last param"
		exit 1
	fi
	fileName=$4
	for i in $@; do
		#statements
		if [[ ! i =~ \/ ]]; then
			#statements
			echo ${#i} | egrep "^8" >>/dev/null && resId=$i
			echo ${#i} | egrep "11" >>/dev/null && resId=$i
			echo ${#i} | egrep "18" >>/dev/null && orderId=$i
			echo $i | egrep "[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}">>/dev/null && now=$i
		fi
	done
fi

#split fileName by keys,the keys we get by the script previous
#echo $resId
#echo $orderId
#echo $now
#echo $fileName

###########get error_logs######################################
#if orderId is not null ,split by orderId first
if [[ -n "$orderId" ]]; then
	b=`cat $fileName | awk '/"'$orderId'"/{print NR}'|tail -1`
	a=$[$b - 300]
	b=$[$b + 100]
	if [[ -n "$now" ]]; then
		cat $fileName | sed -n "${a},${b}p"|sed -n '/ContentCallCenterSync-----/,+57p'
	fi
fi