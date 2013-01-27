package net;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import com.esotericsoftware.kryo.Kryo;

public class ClassRegistrar {
  
  private Kryo kryo;

  public ClassRegistrar(Kryo kryo) {
    this.kryo = kryo;
  }
  
  public void registerByPackage(String ... packages) {
    for(String packageName : packages) {
      try {
        registerByClass(getClassesForPackage(packageName).toArray(new Class<?>[0]));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  private ArrayList<Class<?>> getClassesForPackage(String packageName) throws IOException {
    ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
    
    String folder = packageName.replace('.', '/');
    
    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    ArrayList<URL> directories = Collections.list(contextClassLoader.getResources(folder));
    
    for(URL directory : directories) {
      if(!directory.getPath().contains("test")) {
        ArrayList<Class<?>> classesInDirectory = getClassesForPackageDirectory(packageName, directory);
        classes.addAll(classesInDirectory);
      }
    }
    
    return classes;
  }

  private  ArrayList<Class<?>> getClassesForPackageDirectory(String packageName, URL directoryURL) {
    ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
    File directory = new File(directoryURL.getFile());
    
    for(File file : directory.listFiles()) {
      String filename = file.getName();
      
      if(filename.endsWith(".class")) {
        String nameWithoutType = filename.substring(0, filename.length() - ".class".length());
        String className = packageName + "." + nameWithoutType;
        try {
          classes.add(Class.forName(className));
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
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
