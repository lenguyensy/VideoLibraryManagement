/**
 * UserModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.model;

public interface UserModel extends java.rmi.Remote {
    public sy.video.valueobj.User[] getUsers(int from, int pagesize) throws java.rmi.RemoteException;
    public sy.video.valueobj.User getUser(int userId) throws java.rmi.RemoteException;
    public int getUserByTypeCount(java.lang.String userType) throws java.rmi.RemoteException;
    public sy.video.valueobj.User authenticateUser(java.lang.String email, java.lang.String password) throws java.rmi.RemoteException;
    public int getUsersCount() throws java.rmi.RemoteException;
    public java.lang.String deletUser(int userId) throws java.rmi.RemoteException;
    public java.lang.String addUser(sy.video.valueobj.User u) throws java.rmi.RemoteException;
    public java.lang.String saveUser(sy.video.valueobj.User u) throws java.rmi.RemoteException;
    public java.lang.String resetPassword(sy.video.valueobj.User u) throws java.rmi.RemoteException;
    public sy.video.valueobj.User[] getUserByType(java.lang.String userType, int from, int pagesize) throws java.rmi.RemoteException;
}
