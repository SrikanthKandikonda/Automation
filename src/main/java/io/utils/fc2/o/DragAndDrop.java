package io.utils.fc2.o;

import java.io.BufferedReader;
import java.io.FileReader;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DragAndDrop

{

	public void dragAndDropBasedOnId(String source, String target, WebDriver driver) {
		try {
			String f1 = "." + "/TestData/drag_and_drop_helper.js";
			String java_script = "";
			String text;
			BufferedReader input = new BufferedReader(new FileReader(f1));
			StringBuffer buffer = new StringBuffer();
			while ((text = input.readLine()) != null)
				buffer.append(text + " ");
			java_script = buffer.toString();
			input.close();
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript(java_script + "$('#" + source + "').simulateDragDrop({dropTarget: '#" + target + "'});");
		} catch (Exception rv) {

		}
	}

	public void dragAndDropBasedOnClass(String source, String target, WebDriver driver) {
		try {
			String f1 = "." + "/TestData/drag_and_drop_helper.js";
			String java_script = "";
			String text;
			BufferedReader input = new BufferedReader(new FileReader(f1));
			StringBuffer buffer = new StringBuffer();
			while ((text = input.readLine()) != null)
				buffer.append(text + " ");
			java_script = buffer.toString();
			input.close();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(java_script + "$('#" + source + "').simulateDragDrop({dropTarget: '." + target + "'});");
		} catch (Exception rv) {
			System.out.println(rv.getMessage());
		}
	}

	public void dragAndDropUsingJavaScript(WebElement source, WebElement target, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		final String java_script = "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe"
				+ "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun"
				+ "ction(format,data){this.items[format]=data;this.types.append(for"
				+ "mat);},getData:function(format){return this.items[format];},clea"
				+ "rData:function(format){}};var emit=function(event,target){var ev"
				+ "t=document.createEvent('Event');evt.initEvent(event,true,false);"
				+ "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('"
				+ "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit("
				+ "'drop',tgt);emit('dragend',src);";
		executor.executeScript(java_script, source, target);

	}

	public void dragUsingJavaScript(WebElement source, WebElement target, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		final String java_script = "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe"
				+ "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun"
				+ "ction(format,data){this.items[format]=data;this.types.append(for"
				+ "mat);},getData:function(format){return this.items[format];},clea"
				+ "rData:function(format){}};var emit=function(event,target){var ev"
				+ "t=document.createEvent('Event');evt.initEvent(event,true,false);"
				+ "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('"
				+ "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);";
		executor.executeScript(java_script, source, target);
	}

}
