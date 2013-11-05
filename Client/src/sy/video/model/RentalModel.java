/**
 * RentalModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.model;

public interface RentalModel extends java.rmi.Remote {
    public java.lang.String rentMovie(int userId, int movieId) throws java.rmi.RemoteException;
    public sy.video.valueobj.Rental[] getMoviesRentalByUser(int userId) throws java.rmi.RemoteException;
    public void invalidateExpiredRental() throws java.rmi.RemoteException;
}
