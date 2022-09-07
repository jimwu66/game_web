let questionApp = new Vue({
    el:'#questionApp',
    data:{
        pageInfo:{},
        questions:[],
    },
    methods: {
        loadQuestion: function (pageNum) {
            if(! pageNum){
                pageNum=1;
            }
            $.ajax({
                url: '/v1/question/questions',
                method: 'GET',
                data:{
                    pageNum:pageNum
                },
                success: function (r) {
                    console.log(r);
                    if (r.code === OK) {
                        $("html, body").animate({ scrollTop: 0 }, 100);
                        questionApp.questions=r.data.list;
                        questionApp.pageInfo=r.data;
                    } else {
                        Swal.fire({
                            title: 'no any question data!',
                            showClass: {
                                popup: 'animate__animated animate__fadeInDown'
                            },
                            hideClass: {
                                popup: 'animate__animated animate__fadeOutUp'
                            }
                        })
                    }
                }
            });
        },
        postComment:function(questionId,status){
            if(! questionId) return;
            let postStatus=false;
            if(status===0) {
                postStatus=true;
            }
            let textarea = $('#addComment'+questionId+' textarea');
            let content = textarea.val();
            let nickname = document.getElementById('nickname').innerText
            let icon = userInfo.user.level
            //let icon = document.getElementById('userIcon').src
            let form = {
                nickname : nickname,
                questionId : questionId,
                content : content,
                icon : icon,
                postStatus : postStatus
            }
            $.ajax({
                url:'/v1/comment/postComment',
                method:'POST',
                data: form,
                success:function(r){
                    if(r.code===CREATED){
                        let comment =r.data
                        let questions = questionApp.questions
                        for(let i=0;i<questions.length;i++){
                            if(questions[i].id==questionId){
                                questions[i].comments.push(comment)
                                if(questions[i].status==0){
                                    questions[i].status=1
                                }
                                break;
                            }
                        }
                        $('#addComment'+questionId+' textarea').val('')
                        $('#addComment'+questionId).collapse('toggle');
                    }else{
                        r.message;
                    }
                }
            });
        },
        checkComments:function(commentCount,questionId){
            if(commentCount === 0){
                Swal.fire('no comment data')
                return
            }
            $('#comments'+questionId).toggle()
        },
    },
    created: function () {
        this.loadQuestion(1);
    }
});