package com.kodlamaio.rentACar.core.utilities.aspects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONArray;
import org.json.JSONString;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Aspect
@Component
public class LoggingAspect {
	
/*	@Pointcut("execution(* com.kodlamaio.rentACar.business.concretes.*.*(..))")
	public void myPointcut() {

	}

	@Around("myPointcut()")
	public void applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().getSimpleName().toString();
		Object[] array = pjp.getArgs(); 
//		log.info("date:" + LocalDate.now().getYear() + "\n" + "className " + className + "\n" + " : " + methodName
//				+ "()" + "arguments : " + mapper.writeValueAsString(array));
		Object object = pjp.proceed();
//		log.info(className + " : " + methodName + "()" + "Response : " + mapper.writeValueAsString(object));
		File file = new File("C:\\Logs\\deneme4.json");
		try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true))) {
			bufferWriter.write("\"date\":" + LocalDate.now().getYear());
			bufferWriter.newLine();
			bufferWriter.write("\"className\":" + className);
			bufferWriter.newLine();
			bufferWriter.write("\"methodName\":" + methodName);
			bufferWriter.newLine();
			bufferWriter.write("\"parameters\":" + mapper.writeValueAsString(array));
			bufferWriter.newLine();
		} catch (IOException e) {
			System.out.println("Unable to read file " + file.toString());
		}

	}
*/
	
	
	ArrayList<StringBuilder> denemelik = new ArrayList<StringBuilder>();
	//Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(getClass(), denemelik).create();

	@Before("execution(* com.kodlamaio.rentACar.business.concretes.*.*(..))") 
	public void beforeLog(JoinPoint joinPoint)  
	{ 
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{ date: " + LocalDate.now());
		stringBuilder.append(" className: "+ joinPoint.getTarget().getClass().getSimpleName());
		stringBuilder.append(" methodName: "+ signature.getMethod().getName());
		denemelik.add(stringBuilder);
		if(signature.getMethod().getName()!= "getAll") {
			stringBuilder.append(" parameters: " + joinPoint.getArgs()[0] + " }");
			denemelik.add(stringBuilder);
		}else {
			stringBuilder.append(" parameters: " + "null }");
			denemelik.add(stringBuilder);
		}
		try {
			ObjectMapper mapper = new ObjectMapper();				
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

			//String jsonA = new Gson().toJson(denemelik);--> istenilen format vermedi Gson ile
			//JSONArray jsonArray = new JSONArray(denemelik); --> arraylist var,virgüller doğru, ama süslü parantezler yok

			writer.writeValue(new File("C:\\Logs/deneme3.json"), denemelik);
			/*ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			writer.writeValue(new File("C:\\Logs/deneme.json"),stringBuilder); */
			
			System.out.println(denemelik);
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	
}


/*
package com.kodlamaio.rentACar.core.utilities.aspects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kodlamaio.rentACar.business.concretes.BrandManager;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.Paths;

@Aspect
@Component // classdan nesne üretme işini spring e bırakıyoruz
public class LoggingAspect {
	@Before("execution(* com.kodlamaio.rentACar.business.concretes.*.*(..))")
	public void beforeLog(JoinPoint joinPoint) throws JSONException, IOException {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		StringBuilder builder = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
		
		builder.append(",\n{");
		builder.append(("\n" + "\"date\":") + mapper.writeValueAsString(LocalDate.now().getYear() + "-"+LocalDate.now().getMonthValue() + "-" + LocalDate.now().getDayOfMonth()));
		builder.append("\n" + "\"className\":" + mapper.writeValueAsString(joinPoint.getTarget().getClass().getSimpleName()));
		builder.append("\n" +  "\"methodName\":"  + mapper.writeValueAsString(signature.getMethod().getName()));
		
		if (signature.getMethod().getName() != "getAll") {
			builder.append("\n" + "\"parameters:\":" + mapper.writeValueAsString(joinPoint.getArgs())); // java reflection
		
		} else {
			builder.append("\n" + "\"parameter:\":" + "null");
			
		}
		builder.append("\n" +"}");
		File file = new File("C:\\Logs/deneme.json");

				
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true)) ) {
			bufferedWriter.write(builder.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
*/


	



