var SoundNotify = SoundNotify || {
  filepath:'/static/theme/'+keyWords+'/sound/end.mp3',
  init:function(){
    var s = this;
    if ($('#sound_element').size()>0 && (typeof defaultSoundMute !== 'undefined' && !defaultSoundMute)) {
      var audio = new Audio(s.filepath);
      audio.play();
    }
  }
};