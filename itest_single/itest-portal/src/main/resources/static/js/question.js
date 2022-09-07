Vue.component('v-select', VueSelect.VueSelect);
let createQuestionApp = new Vue({
    el: '#createQuestionApp',
    data: {
        title: '',
        tags: [],
        selectedTags: []
    },
    methods: {
        loadTag: function () {
            $.ajax({
                url:'/v1/question/tags',
                method: 'GET',
                success:function(r){
                    console.log(r);
                    if(r.code==OK){
                        let tagsName = []
                        let tags = r.data
                        for(let i=0;i<tags.length;i++){
                            tagsName.push(tags[i].name);
                        }
                        createQuestionApp.tags = tagsName
                    }else{
                        console.log(r.message);
                    }
                }
            });
        },
        createQuestion:function (){
            let content = $('#summernote').val()
            let data = {
                    title:this.title,
                    nickname:userInfo.user.nickname,
                    icon:userInfo.user.level,
                    selectedTags: this.selectedTags,
                    content:content
            }
            console.log(data)
            $.ajax({
                url:'/v1/question/create',
                method:'POST',
                data:data,
                success:function (r){
                    console.log(r)
                    if(r.code===CREATED){
                        createQuestionApp.title = '';
                        createQuestionApp.selectedTags = [];
                        $('#summernote').summernote('reset');
                        $('#createQuestionApp').toggle();
                        questionApp.loadQuestion()
                        Swal.fire(r.message);
                    }else{
                        Swal.fire(r.message)
                    }
                }
            });
        },
    },
    created: function () {
        this.loadTag();
    }
});
$('#summernote').summernote({
    placeholder: 'write your question here',
    tabsize: 2,
    height: 120,
    toolbar: [
        ['style', ['style']],
        ['font', ['bold', 'underline', 'clear']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['table', ['table']],
        ['insert', ['link', 'picture', 'video']],
        ['view', ['fullscreen', 'codeview', 'help']]
    ]
});
