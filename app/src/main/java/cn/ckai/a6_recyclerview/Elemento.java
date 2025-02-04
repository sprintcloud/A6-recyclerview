package cn.ckai.a6_recyclerview;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Elemento {
    @PrimaryKey(autoGenerate = true)
    int id;
    private String nombre_libro;
    private String nombre_author;
    private String descripcion;
    private String ISBN;
    private String imageResourceId;
    float valoracion;

    public Elemento(String nombre_libro, String descripcion, String ISBN, String nombre_author, String imageId) {

        this.nombre_libro = nombre_libro;
        this.descripcion = descripcion;
        this.ISBN = ISBN;
        this.nombre_author = nombre_author;
        this.imageResourceId = imageId;
    }

    public Elemento(String nombre_libro, String descripcion, String ISBN, String nombre_author) {
        this.nombre_libro = nombre_libro;
        this.descripcion = descripcion;
        this.ISBN = ISBN;
        this.nombre_author = nombre_author;
    }

    public void setImageResourceId(String imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getImageResourceId() {
        return imageResourceId;
    }

    public void setNombre_libro(String nombre_libro) {
        this.nombre_libro = nombre_libro;
    }

    public String getNombre_libro() {
        return nombre_libro;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setNombre_author(String nombre_author) {
        this.nombre_author = nombre_author;
    }

    public String getNombre_author() {
        return nombre_author;
    }
}
