class Locus {
  addMarker(points) {
    var pointsLen = points.length;
    if (pointsLen == 0) {
      return;
    }
    var myIcon = new window.BMap.Icon(
      "track.ico",
      new window.BMap.Size(5, 5), {
        offset: new window.BMap.Size(5, 5)
      }
    );
    // 创建标注对象并添加到地图
    for (var i = 0; i < pointsLen; i++) {
      var point = new window.BMap.Point(points[i].lng, points[i].lat);
      var marker = new window.BMap.Marker(point, {
        icon: myIcon
      });
      this.map.addOverlay(marker);
    }
  }

  addLine(points) {
    let linePoints = [],
      pointsLen = points.length,
      i,
      polyline;
    if (pointsLen === 0) {
      return;
    }
    // 创建标注对象并添加到地图
    for (i = 0; i < pointsLen; i++) {
      linePoints.push(new window.BMap.Point(points[i].lng, points[i].lat));
    }
    polyline = new window.BMap.Polyline(linePoints, {
      strokeColor: "red",
      strokeWeight: 2,
      strokeOpacity: 0.5
    }); // 创建折线
    this.map.addOverlay(polyline);
  }

  dynamicLine() {
    var lng = 116 + this.getRandom();
    var lat = 39 + this.getRandom();
    var id = this.getRandom(1000);
    var point = {
      lng: lng,
      lat: lat,
      status: 1,
      id: id
    };
    var makerPoints = [];
    var newLinePoints = [];
    var len;
    makerPoints.push(point);
    this.addMarker(makerPoints); // 增加对应该的轨迹点
    this.points.push(point);
    this.bPoints.push(new window.BMap.Point(lng, lat));
    len = this.points.length;
    newLinePoints = this.points.slice(len - 2, len); // 最后两个点用来画线。

    this.addLine(newLinePoints); // 增加轨迹线
    this.setZoom(this.bPoints);
    setTimeout(this.dynamicLine, 6000);
  }

  getRandom() {
    return Math.random() * 0.1;
  }

  setZoom(bPoints) {
    var view = this.map.getViewport(eval(bPoints));
    var mapZoom = view.zoom;
    var centerPoint = view.center;
    this.map.centerAndZoom(centerPoint, mapZoom);
  }
}
export default new Locus();
