package model;

public class Carteira {

    private int id;
    private int id_usuario;
    private int id_categaria;

    private double valor;
    private String comentario;

    public Carteira() {
    }

    public Carteira(int id, int id_usuario, int id_categaria, double valor, String comentario) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_categaria = id_categaria;
        this.valor = valor;
        this.comentario = comentario;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the id_categaria
     */
    public int getId_categaria() {
        return id_categaria;
    }

    /**
     * @param id_categaria the id_categaria to set
     */
    public void setId_categaria(int id_categaria) {
        this.id_categaria = id_categaria;
    }

    /**
     * @return the entrada
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the entrada to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }


    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
