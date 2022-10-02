let gameApp = new Vue({
    el:'#gameApp',
    data: {
        scores1: [],
        scores2: []
    },
    methods: {
        loadGame1Scores:function (){
            let gameName = document.getElementById('game1_title').innerText
            $.ajax({
                url:'/v1/score/'+gameName,
                method:'GET',
                success:function(r){
                    if(r.code==OK){
                        console.log(r);
                        gameApp.scores1 = updateDuration(r.data);
                    }else{
                        r.message;
                    }
                }
            });
        },
        loadGame2Scores:function (){
            let gameName = document.getElementById('game2_title').innerText
            $.ajax({
                url:'/v1/score/'+gameName,
                method:'GET',
                success:function(r){
                    if(r.code==OK){
                        console.log(r);
                        gameApp.scores2 = updateDuration(r.data);
                    }else{
                        r.message;
                    }
                }
            });
        },

    },
    created:function(){
        this.loadGame1Scores();
        this.loadGame2Scores();
    }
});
/*
let game2App = new Vue({
    el:'#game2App',
    data: {
        scores: []
    },
    methods: {
        loadScores:function (){
            let gameName = document.getElementById('game2_title').innerText
            $.ajax({
                url:'/v1/score/'+gameName,
                method:'GET',
                success:function(r){
                    console.log(r);
                    if(r.code==OK){
                        game2App.scores=r.data;
                        game2App.updateDuration();
                    }else{
                        r.message;
                    }
                }
            });
        },
        updateDuration:function (){
            for(let i=0;i<game2App.scores.length;i++){
                addDuration(game2App.scores[i]);
            }
        }
    },
    created:function(){
        this.loadScores();
    }
});
*/
/*
function addDuration(item){
    if(!item || !item.duration) {
        alert('no duration data!');
        return;
    }
    let time = item.duration;
    item.duration = Math.round(time/1000)+'sec';
};
*/
function updateDuration(scores){
    for(let i=0;i< scores.length;i++){
        let time = scores[i].duration
        scores[i].duration = Math.round(time/1000)+'sec';
    }
    return scores
};
//v1.2 fixed top bar function
document.addEventListener("scroll", function () {
    const topBar = document.querySelector(".index_top_bar");
    let top = document.documentElement.scrollTop;
    console.log(top);
    if (top > 50) {
        topBar.style.position ="fixed";
        topBar.style.top = "0px";
    } else {
        topBar.style.position ="relative";
        topBar.style.removeProperty("top");
    }
});
document.querySelector(".snake").addEventListener('click',function(){
    console.log('click');
    location.href="./snake.html";
});
document.querySelector(".tetris").addEventListener('click',function(){
    console.log('click');
    location.href="./tetris.html";
});