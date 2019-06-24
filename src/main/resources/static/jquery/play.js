function ONOFF() {
    if(music.paused){
        $("#icon").html("&#xe651;");
        music.play();
    }else{
        $("#icon").html("&#xe652;");
        music.pause();
    }
};