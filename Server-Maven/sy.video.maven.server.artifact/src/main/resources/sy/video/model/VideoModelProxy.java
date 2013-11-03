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
  
  
}