//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.15 at 10:31:29 AM CEST 
//


package org.promasi.game.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employeeTaskModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employeeTaskModel">
 *   &lt;complexContent>
 *     &lt;extension base="{}model">
 *       &lt;sequence>
 *         &lt;element name="_projectTaskName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_firstStep" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_lastStep" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_dependencies" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_taskName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeTaskModel", propOrder = {
    "projectTaskName",
    "firstStep",
    "lastStep",
    "dependencies",
    "taskName"
})
public class EmployeeTaskModel
    extends Model
{

    @XmlElement(name = "_projectTaskName")
    protected String projectTaskName;
    @XmlElement(name = "_firstStep")
    protected int firstStep;
    @XmlElement(name = "_lastStep")
    protected int lastStep;
    @XmlElement(name = "_dependencies", nillable = true)
    protected List<String> dependencies;
    @XmlElement(name = "_taskName")
    protected String taskName;

    /**
     * Gets the value of the projectTaskName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectTaskName() {
        return projectTaskName;
    }

    /**
     * Sets the value of the projectTaskName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectTaskName(String value) {
        this.projectTaskName = value;
    }

    /**
     * Gets the value of the firstStep property.
     * 
     */
    public int getFirstStep() {
        return firstStep;
    }

    /**
     * Sets the value of the firstStep property.
     * 
     */
    public void setFirstStep(int value) {
        this.firstStep = value;
    }

    /**
     * Gets the value of the lastStep property.
     * 
     */
    public int getLastStep() {
        return lastStep;
    }

    /**
     * Sets the value of the lastStep property.
     * 
     */
    public void setLastStep(int value) {
        this.lastStep = value;
    }

    /**
     * Gets the value of the dependencies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dependencies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDependencies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDependencies() {
        if (dependencies == null) {
            dependencies = new ArrayList<String>();
        }
        return this.dependencies;
    }

    /**
     * Gets the value of the taskName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the value of the taskName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskName(String value) {
        this.taskName = value;
    }

}
