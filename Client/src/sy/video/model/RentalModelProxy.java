package sy.video.model;

public class RentalModelProxy implements sy.video.model.RentalModel {
  private String _endpoint = null;
  private sy.video.model.RentalModel rentalModel = null;
  
  public RentalModelProxy() {
    _initRentalModelProxy();
  }
  
  public RentalModelProxy(String endpoint) {
    _endpoint = endpoint;
    _initRentalModelProxy();
  }
  
  private void _initRentalModelProxy() {
    try {
      rentalModel = (new sy.video.model.RentalModelServiceLocator()).getRentalModel();
      if (rentalModel != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)rentalModel)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)rentalModel)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (rentalModel != null)
      ((javax.xml.rpc.Stub)rentalModel)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public sy.video.model.RentalModel getRentalModel() {
    if (rentalModel == null)
      _initRentalModelProxy();
    return rentalModel;
  }
  
  public void invalidateExpiredRental() throws java.rmi.RemoteException{
    if (rentalModel == null)
      _initRentalModelProxy();
    rentalModel.invalidateExpiredRental();
  }
  
  public java.lang.String rentMovie(int userId, int movieId) throws java.rmi.RemoteException{
    if (rentalModel == null)
      _initRentalModelProxy();
    return rentalModel.rentMovie(userId, movieId);
  }
  
  public sy.video.valueobj.Rental[] getMoviesRentalByUser(int userId) throws java.rmi.RemoteException{
    if (rentalModel == null)
      _initRentalModelProxy();
    return rentalModel.getMoviesRentalByUser(userId);
  }
  
  public sy.video.valueobj.User[] getUserByMovieId(int movieId) throws java.rmi.RemoteException{
    if (rentalModel == null)
      _initRentalModelProxy();
    return rentalModel.getUserByMovieId(movieId);
  }
  
  
}