/**
 * VideoModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.model;

public interface VideoModel extends java.rmi.Remote {
    public sy.video.valueobj.Movie getMovie(int movieId) throws java.rmi.RemoteException;
    public sy.video.valueobj.Movie[] getMovies() throws java.rmi.RemoteException;
    public int addMovie(sy.video.valueobj.Movie m) throws java.rmi.RemoteException;
    public int deletMovie(int movieId) throws java.rmi.RemoteException;
    public void saveMovie(sy.video.valueobj.Movie m) throws java.rmi.RemoteException;
}
