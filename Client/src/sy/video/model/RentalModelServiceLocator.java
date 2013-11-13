/**
 * RentalModelServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.model;

public class RentalModelServiceLocator extends org.apache.axis.client.Service implements sy.video.model.RentalModelService {

    public RentalModelServiceLocator() {
    }


    public RentalModelServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RentalModelServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RentalModel
    private java.lang.String RentalModel_address = "http://localhost:8080/VideoLibraryServer/services/RentalModel";

    public java.lang.String getRentalModelAddress() {
        return RentalModel_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RentalModelWSDDServiceName = "RentalModel";

    public java.lang.String getRentalModelWSDDServiceName() {
        return RentalModelWSDDServiceName;
    }

    public void setRentalModelWSDDServiceName(java.lang.String name) {
        RentalModelWSDDServiceName = name;
    }

    public sy.video.model.RentalModel getRentalModel() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RentalModel_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRentalModel(endpoint);
    }

    public sy.video.model.RentalModel getRentalModel(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            sy.video.model.RentalModelSoapBindingStub _stub = new sy.video.model.RentalModelSoapBindingStub(portAddress, this);
            _stub.setPortName(getRentalModelWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRentalModelEndpointAddress(java.lang.String address) {
        RentalModel_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (sy.video.model.RentalModel.class.isAssignableFrom(serviceEndpointInterface)) {
                sy.video.model.RentalModelSoapBindingStub _stub = new sy.video.model.RentalModelSoapBindingStub(new java.net.URL(RentalModel_address), this);
                _stub.setPortName(getRentalModelWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RentalModel".equals(inputPortName)) {
            return getRentalModel();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://model.video.sy", "RentalModelService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://model.video.sy", "RentalModel"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RentalModel".equals(portName)) {
            setRentalModelEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
