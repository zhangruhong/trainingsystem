package com.synnex.utils.mailUtils;

import org.junit.Test;

import com.synnex.utils.mailUtil.SendEmailThread;

public class sendmailtest {

	@Test
	public void testmailsend() {
		SendEmailThread set = new SendEmailThread("测试主题1", "zhangruhong163@163.com",
				"1这是正文<br/>单击用于翻译主题中字符串的按钮,您将进入字符串翻译页面,该页面只显示主题的文本。您可以选择具体要翻译的字符串或翻译所有缺少译文的字符串。主题和插件的专业翻译..");
		set.start();
	}

}
