package sy.video.model;

public class VideoModelProxy implements sy.video.model.VideoModel {
  private String _endpoint = null;
  private sy.video.model.VideoModel videoModel = null;
  
  public VideoModelProxy() {
    _initVideoModelProxy();
  }
  
  public VideoModelProxy(String endpoint) {
    _endpoint = endpoint;
    _initVideoModelProxy();
  }
  
  private void _initVideoModelProxy() {
    try {
      videoModel = (new sy.video.model.VideoModelServiceLocator()).getVideoModel();
      if (videoModel != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)videoModel)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)videoModel)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (videoModel != null)
      ((javax.xml.rpc.Stub)videoModel)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public sy.video.model.VideoModel getVideoModel() {
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel;
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    videoModel.main(args);
  }
  
  public sy.video.valueobj.Movie[] getMovies(int from, int pagesize) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.getMovies(from, pagesize);
  }
  
  public int getMoviesCount() throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.getMoviesCount();
  }
  
  public sy.video.valueobj.Movie[] getMoviesByGenre(java.lang.String genre, int from, int pagesize) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.getMoviesByGenre(genre, from, pagesize);
  }
  
  public int getMoviesByGenreCount(java.lang.String genre) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.getMoviesByGenreCount(genre);
  }
  
  public sy.video.valueobj.Movie[] getMoviesBySearchTerm(java.lang.String searchTerm, int from, int pagesize) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.getMoviesBySearchTerm(searchTerm, from, pagesize);
  }
  
  public int getMoviesBySearchTermCount(java.lang.String searchTerm) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.getMoviesBySearchTermCount(searchTerm);
  }
  
  public sy.video.valueobj.Movie getMovie(int movieId) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.getMovie(movieId);
  }
  
  public java.lang.String addMovie(sy.video.valueobj.Movie m) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.addMovie(m);
  }
  
  public java.lang.String deletMovie(int movieId) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.deletMovie(movieId);
  }
  
  public java.lang.String saveMovie(sy.video.valueobj.Movie m) throws java.rmi.RemoteException{
    if (videoModel == null)
      _initVideoModelProxy();
    return videoModel.saveMovie(m);
  }
  
  
}