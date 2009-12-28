// 教程中的代码就写在这个区域里.
var dsConfig= {
    fields :[
        {name : 'name' },
        {name : 'desc' },
        {name : 'count' , type: 'int'}
    ]
};

var colsConfig = [
    { id : 'name'      , header : "分类" },
    { id : 'desc'      , header : "描述" },
    { id : 'count'    , header : "次数" }    
];

var gridConfig={

    id : "grid1",
        
    dataset : dsConfig ,

    columns : colsConfig ,

    container : 'grid1_container', 

    toolbarPosition : 'bottom',

    toolbarContent : 'nav | goto | pagesize | reload | state',
    
    pageSize : 30 ,
    
    pageSizeList : [30,40,50],
    
    loadURL: 'demo.gt_grid',
    
    remotePaging: false
};

var mygrid=new GT.Grid( gridConfig );

GT.Utils.onLoad( GT.Grid.render(mygrid) );