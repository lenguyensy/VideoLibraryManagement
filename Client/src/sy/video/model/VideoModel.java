/**
 * VideoModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.model;

public interface VideoModel extends java.rmi.Remote {
    public sy.video.valueobj.Movie[] getMovies(int from, int pagesize) throws java.rmi.RemoteException;
    public sy.video.valueobj.Movie[] getMoviesByGenre(java.lang.String genre, int from, int pagesize) throws java.rmi.RemoteException;
    public sy.video.valueobj.Movie[] getMoviesBySearchTerm(java.lang.String searchTerm, int from, int pagesize) throws java.rmi.RemoteException;
    public sy.video.valueobj.Movie getMovie(int movieId) throws java.rmi.RemoteException;
    public java.lang.String addMovie(sy.video.valueobj.Movie m) throws java.rmi.RemoteException;
    public java.lang.String deletMovie(int movieId) throws java.rmi.RemoteException;
    public java.lang.String saveMovie(sy.video.valueobj.Movie m) throws java.rmi.RemoteException;
}
