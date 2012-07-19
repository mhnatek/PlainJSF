/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.hnatek.plainjsf;

/**
 *
 * @author Markus
 */
public class MyData {

    // Init --------------------------------------------------------------------------------------

    private Long id;
    private String name;
    private String value;

    public MyData() {
    }

    
    public MyData(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
    
    // Getters -----------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    // Setters -----------------------------------------------------------------------------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    // Overrides -----------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "id=" + id + ", name='" + name + "', value='" + value + "'";
    }
}
