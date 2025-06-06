package com.example.mvvmconcept.core.utils;

/**
 * Wrapper générique pour représenter l'état d'une ressource.
 * @param <T> le type des données contenues
 */
public class Resource<T> {

    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    public final Status status;
    public final T data;
    public final String message;

    private Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(Status.LOADING, data, null);
    }
}
