package br.com.gostoudaaula.convert.json;

/**
 * Created by alexf on 27/12/2015.
 */
public interface Convert<T> {

    public T toObject(String string);

    public String toJson(T t);}
