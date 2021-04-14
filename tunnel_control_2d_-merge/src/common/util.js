class Util {
/**
   * 格式化日期时间
   *
   * @param {object || string} time         时间对象或者时间字符串(yyyy-MM-dd类型的)
   * @param {string} formateStr   要格式化的字符串,例如“yyyy-MM-dd hh:mm:ss”
   *
   * */
  formatTime (timeStr, formateStr) {
    const time = new Date(timeStr);
    if (!this.isDate(time)) {
      throw new Error('invalid parameter');
    }
    const timeObj = {
      'M+': time.getMonth() + 1,
      'd+': time.getDate(),
      'h+': time.getHours(),
      'm+': time.getMinutes(),
      's+': time.getSeconds()
    };
    if (/(y+)/.test(formateStr)) {
      formateStr = formateStr.replace(RegExp.$1, (time.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (let key in timeObj) {
      let reg = new RegExp(key);
      if (reg.test(formateStr)) {
        formateStr = formateStr.replace(reg, timeObj[key] >= 10 ? timeObj[key] : '0' + timeObj[key]);
      }
    }
    return formateStr;
  }

  /**
   * 判断是否是数组
   * */
  isArray (input) {
    return input instanceof Array || Object.prototype.toString.call(input) === '[object Array]';
  }

  /*
  * 判断是否是日期
  * */
  isDate (input) {
    return input instanceof Date || Object.prototype.toString.call(input) === '[object Date]';
  }

  /**
   * 判断是否是函数
   * */
  isFunction (input) {
    return input instanceof Function || Object.prototype.toString.call(input) === '[object Function]';
  }

  /**
   * 判断是否是对象
   * */
  isObject (input) {
    return input != null && Object.prototype.toString.call(input) === '[object Object]';
  }

  /**
   * 判断是否是空对象
   * */
  isObjectEmpty (obj) {
    if (Object.getOwnPropertyNames) {
      return (Object.getOwnPropertyNames(obj).length === 0);
    } else {
      var k;
      for (k in obj) {
        if (obj.hasOwnProperty(k)) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * 实时日期显示
   *
   */
  clockon () {
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var day = now.getDay();
    var hour = now.getHours();
    var minu = now.getMinutes();
    var sec = now.getSeconds();
    var week;
    if (month < 10) month = '0' + month;
    if (date < 10) date = '0' + date;
    if (hour < 10) hour = '0' + hour;
    if (minu < 10) minu = '0' + minu;
    if (sec < 10) sec = '0' + sec;
    var arrWeek = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
    week = arrWeek[day];
    return `${year + '-' + month + '-' + date} ${week} ${hour + ':' + minu + ':' + sec}`;
  }
}

export default new Util();
