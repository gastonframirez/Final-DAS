
package ar.edu.ubp.das.src.comercio.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.2.4
 * Sun Feb 03 18:57:15 ART 2019
 * Generated source version: 3.2.4
 */

@XmlRootElement(name = "getOfertasResponse", namespace = "http://ws.comercio.src.das.ubp.edu.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOfertasResponse", namespace = "http://ws.comercio.src.das.ubp.edu.ar/")

public class GetOfertasResponse {

    @XmlElement(name = "return")
    private java.util.List<ar.edu.ubp.das.src.beans.OfertaResponseBean> _return;

    public java.util.List<ar.edu.ubp.das.src.beans.OfertaResponseBean> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<ar.edu.ubp.das.src.beans.OfertaResponseBean> new_return)  {
        this._return = new_return;
    }

}

