package com.example.trabajarconapi;

public class Evaluador {

    private String idevaluador;
    private String area;
    private String imgJPG;
    private String imgjpg;
    private String nombres;

    public Evaluador(String idevaluador, String area, String imgJPG, String imgjpg, String nombres) {
        this.idevaluador = idevaluador;
        this.area = area;
        this.imgJPG = imgJPG;
        this.imgjpg = imgjpg;
        this.nombres = nombres;
    }

    public String getIdevaluador() {
        return idevaluador;
    }

    public void setIdevaluador(String idevaluador) {
        this.idevaluador = idevaluador;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImgJPG() {
        return imgJPG;
    }

    public void setImgJPG(String imgJPG) {
        this.imgJPG = imgJPG;
    }

    public String getImgjpg() {
        return imgjpg;
    }

    public void setImgjpg(String imgjpg) {
        this.imgjpg = imgjpg;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
