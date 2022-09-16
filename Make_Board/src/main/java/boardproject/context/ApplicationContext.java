package boardproject.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;

import javax.naming.Context;
import javax.naming.InitialContext;

import boardproject.annotation.Component;

public class ApplicationContext {
	
	// 객체를 저장할 해시테이블 objTable
	Hashtable<String, Object> objTable = new Hashtable<String, Object>();
	
	// 해시테이블에서 객체를 꺼낼 getter 메서드
	public Object getBean(String key) {
		return objTable.get(key);
	}
	
	// 외부에서 등록할 수 있게
	public void addBean(String name, Object obj) {
		objTable.put(name, obj);
	}
	
	
	// 객체 준비
	public void prepareObjectsByProperties(String propertiesPath) throws Exception {
		
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));
		
		// JNDI 객체를 찾을 때 사용할 InitialContext
		Context context = new InitialContext();
		String key = null;
		String value = null;
		
		// 반복문을 통해 프로퍼티에 있는 정보들을 꺼내 객체를 생성
		for (Object item : props.keySet()) {
			key = (String) item;
			value = props.getProperty(key);
			
			// jndi.으로 시작하는 건 객체를 톰캣서버에서 제공하는 객체라서 새로 만들지 않고 찾기
			if (key.startsWith("jndi.")) {
				objTable.put(key, context.lookup(value));
			} else {
				objTable.put(key, Class.forName(value).newInstance());
			}
		}
	}
	
	// annotation으로 부터 값을 추출하여 객체을 자동 생성
	public void prepareObjectByAnnotation(String basePackage) throws Exception {
		Reflections reflector = new Reflections(basePackage);
		
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key = null;
		
		for(Class<?> clazz : list) {
			key = clazz.getAnnotation(Component.class).value();
			objTable.put(key, clazz.newInstance());
		}
	}
	
	
	
	// 톰캣 서버로부터 객체를 가져오거나 (ex:DataSource)
	// 직접 생성하면 (ex:PostDao)
	// 각 객체가 필요로하는 의존 객체 할당
	public void injectDependency() throws Exception {
		for (String key:objTable.keySet()) {
			// jndi.로 시작하는 건 톰캣서버에서 제공하는 객체이기 때문에 의존성 주입하면 안됨
			if (!key.startsWith("jndi.")) { 
				callSetter(objTable.get(key));
			}
		}
	}
	
	// callSetter는 매개변수로 주어진 객체에 대해 Setter 메서드를 찾아서 호출
	private void callSetter(Object obj) throws Exception {
		Object dependency = null;
		for (Method method : obj.getClass().getMethods() ) {
			if (method.getName().startsWith("set")) {
				dependency = findObjectByType(method.getParameterTypes()[0]);
				if (dependency != null) {
					method.invoke(obj, dependency);
				}
			}
		}
	}
	
	// Setter 메서드를 호출할 때 넘겨줄 의존 객체를 찾는 일
	private Object findObjectByType(Class<?> type) {
		for (Object obj:objTable.values()) {
			if (type.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
}
