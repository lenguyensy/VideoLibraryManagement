/**
 * UserModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.model;

public interface UserModel extends java.rmi.Remote {
    public sy.video.valueobj.User getUser(int userId) throws java.rmi.RemoteException;
    public void saveUser(sy.video.valueobj.User u) throws java.rmi.RemoteException;
    public int deletUser(int userId) throws java.rmi.RemoteException;
    public int addUser(sy.video.valueobj.User u) throws java.rmi.RemoteException;
    public sy.video.valueobj.User[] getSimpleCustomers() throws java.rmi.RemoteException;
    public sy.video.valueobj.User[] getPremiumMembers() throws java.rmi.RemoteException;
}
