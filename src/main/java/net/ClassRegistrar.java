package net;

import java.io.File;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;

public class ClassRegistrar {
  
  private Kryo kryo;

  public ClassRegistrar(Kryo kryo) {
    this.kryo = kryo;
  }
  
  public void registerByPackage(String ... packages) {
    for(String packageName : packages) {
      try {
        registerByClass(getPackageClasses(packageName).toArray(new Class<?>[0]));
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
  
  private ArrayList<Class<?>> getPackageClasses(String packageName) throws ClassNotFoundException {
    ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
    
    String folder = packageName.replace('.', '/');
    
    File directory = new File(Thread.currentThread().getContextClassLoader().getResource(folder).getFile());
      
    if(directory.exists()) {
      for(File file : directory.listFiles()) {
        String filename = file.getName();
        if(filename.endsWith(".class")) {
          filename = filename.substring(0, filename.length() - ".class".length());
          String className = packageName + "." + filename;
          classes.add(Class.forName(className));
        }
      }
    }
    
    return classes;
  }

  public void registerByClass(Class<?> ... classes) {
    for(Class<?> klass : classes) {
      kryo.register(klass);
    }
  }
}
