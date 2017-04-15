package com.telan.weixincenter.utils;

//临时文案工具
public class AnswerUtils {

	public static String getDefaultMessage() {
		String message = "你好，欢迎来到特兰有问题，我有问题，你有答案吗？目前只公众号只开放了简单的题目问答功能，输入‘帮助’查看答题方法";
		return message;
	}

	public static String getHelpMessage() {
		String message = "输入‘答题’获取一道题目，输入‘（题目编号）#（答案）’答题。如题目为‘编号<123> 请问1+1=？’，则回复‘123#2’即可。来试试吧~";
		return message;
	}


	public static String getSuccessMessage() {
		String message = "回答正确！";
		return message;
	}

	public static String getFailMessage() {
		String message = "回答错误！";
		return message;
	}

	public static String getAnswerMessage(int questionNo) {
		String message="特兰真帅";
		switch (questionNo) {
			case 1:
				message="130";
				break;
			case 2:
				message="";
				break;
			default:
				break;
		}
		return message;
	}

	public static String getQuestionMessage() {
		String question = "1.我站在路口，左前方是一家医院，左后方写着优才教育。此时刚好右前方一家剧院12月11号最后一场动画片开始放映，请问现在我的手表时针和分针夹角是多少度？";
		return question;
	}
}
