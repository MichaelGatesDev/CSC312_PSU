package csc312.web;

public interface Callback<T>
{
    void onComplete(T result);
}