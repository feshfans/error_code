!function(window,document,$,undefined){
    $(function(){

        $('#terminal-manager').click(function () {
          $('#code').css("display","none");
          $('#terminal').css("display","block");
          terminal.getAll();

        });


        //模块管理相关代码 start
        $('#terminalModal').on('show.bs.modal',function (event) {

            var button = $(event.relatedTarget) ;
            var op = button.data('op');
            var modal = $(this);
            modal.find('.modal-title').text(op+"模块");


            var btnId=button.attr('id');

            if(btnId.indexOf("update")!=-1){
                var selected=terminal.getSelected();
                if(selected.length!=1){
                    alert("请选中一个模块");
                    //$('#terminalModal').modal('toggle');
                    event.preventDefault();
                    return ;
                }

                var data=terminal.getDataById(selected[0]);
                terminal.render(data);
            }else{
                terminal.reset();
            }


        });

        $('#save-terminal').click(function () {

            if($('#terminal-id').val()){
                terminal.update();
            }else{
                terminal.add();
            }


            $('#terminalModal').modal("toggle");


        });

        $('#delete-terminal').click(function () {

            var selected=terminal.getSelected();
            if(selected.length==0){
                alert("请选中条目");
                return ;
            }

            terminal.delete(selected);
        });

        var terminal={

            add:function () {
                $.ajax({
                    type: "POST",
                    url: "terminal/add.do",
                    data: $('#terminal-form').serialize(),
                    success: function (data) {
                        if(data=="-1"){
                            alert("模块名已存在，请检查");
                            return ;
                        }
                        if(data=="0"){
                            alert("添加失败");
                            return ;
                        }
                        terminal.getAll();
                    }

                });
            },

            update:function () {
                $.ajax({
                    type: "POST",
                    url: "terminal/update.do",
                    data: $('#terminal-form').serialize(),
                    success: function (data) {
                        if(data=="0"){
                            alert("添加失败");
                        }
                        terminal.getAll();
                    }

                });
            },
            reset:function () {
                $('#terminal-id').val();
                $('#terminal-form')[0].reset();
            },
            delete:function (ids) {
                for(var index in ids){
                    $.ajax({
                        type: "POST",
                        url: "terminal/delete.do",
                        data: {"id":ids[index]},
                        success: function (data) {
                            terminal.getAll();
                            if(data=="-1"){
                                alert("错误码有对此模块的引用,请先删除错误码");
                            }
                            if(data=="0"){
                                alert("删除出错，请与开发人员联系");
                            }
                        }

                    });
                }

            },
            getSelected:function () {
                var result=[];
                $("[name = terminalBox]:checkbox").each(function (index) {

                    if($(this).is(':checked')){
                        result.push($(this).val());
                    }
                })

                return result;
            },
            getDataById:function (id) {
                var data={};
                var tds=$('#'+id).find("td");

                data.id=id;
                data.name=$(tds[1]).text();
                data.responsibleName=$(tds[2]).text();
                data.projectName=$(tds[3]).text();

                return data;
            },
            getAll:function () {
                var data=[];
                $.ajax({
                    type: "POST",
                    url: "terminal/all.do",
                    async:false,
                    success: function (jsonArray) {
                        data=jsonArray;

                        var table="";
                        for(var index in jsonArray){
                            var json=jsonArray[index];

                            table+='<tr id="'+json.id+'">'+
                                '<th scope="row">'+(parseInt(index)+1)+'</th>'+
                                '<td><input type="checkbox" name="terminalBox" value="'+json.id+'"/> </td>'+
                                '<td>'+json.name+'</td>'+
                                '<td>'+json.responsibleName+'</td>'+
                                '<td>'+json.projectName+'</td>'+
                                '</tr>';
                        }

                        $('#terminal-tbody').html(table);
                    }

                });
                return data;
            },
            render:function (data) {
                $('#terminal-id').val(data.id);
                $('#terminal-name').val(data.name);
                $('#terminal-responsible').val(data.responsibleName);
                $('#project-name').val(data.projectName);

            }
        }

        //模块管理相关代码 end


        //错误码管理相关代码 start


        $('#code-manager').click(function () {
            codeManager.view.show();
            codeManager.buss.load();
        });

        $('#codeModal').on('show.bs.modal',function (event) {

            var button = $(event.relatedTarget) ;
            var op = button.data('op');

            codeManager.view.setModalTitle(op+"错误码");

            var btnId=button.attr('id');

            if(btnId.indexOf("update")!=-1){
                var selected=codeManager.view.getSelected();
                if(selected.length!=1){
                    alert("请选中一条错误码");
                    event.preventDefault();
                    return ;
                }

                var data=codeManager.view.getDataByIdFromView(selected[0]);

                codeManager.view.renderForm(data);
            }else{
                codeManager.view.reset();
            }
        });

        $('#save-code').click(function () {
            if($('#code-id').val()){
                codeManager.model.update();
            }else {

                codeManager.model.add();
            }

            codeManager.view.hiddenModal();

            codeManager.buss.loadTable();
        });

        $('#delete-code').click(function () {
            var selected=codeManager.view.getSelected();
            if(selected.length==0){
                alert("请至少选中一条错误码");
                return ;
            }


            codeManager.model.delete(selected);
            codeManager.buss.loadTable();

        });

        $('#code-type-terminal').change(function () {
            var terminal=$(this).val();
            var data=[];
            if(terminal=="0"){
                data=codeManager.model.getAll();
            }else{
                data=codeManager.model.getListByTerminal(terminal);
            }
            codeManager.view.renderTable(data);

        });

        $('#code-search').keypress(function () {
            var data=codeManager.model.search($(this).val());

            codeManager.view.renderTable(data);
        });

        var codeManager={
            buss:{
                loadTable:function () {
                    var codeDatas=codeManager.model.getAll();
                    codeManager.view.renderTable(codeDatas);
                },
                load:function () {
                    var data= terminal.getAll();

                    codeManager.view.renderTerminal(data);

                    codeManager.buss.loadTable();
                }

            },
            model:{
                add:function () {
                    $.ajax({
                        type: "POST",
                        url: "code/add.do",
                        async:false,
                        data: $('#code-form').serialize(),
                        success: function (data) {
                            if(0==data.code){
                                alert(data.message);
                            }
                            if(-1==data.code){
                                alert("错误码已存在，请检查");
                            }

                        }

                    });
                },
                update:function () {
                    $.ajax({
                        type: "POST",
                        url: "code/update.do",
                        async:false,
                        data: $('#code-form').serialize(),
                        success: function (data) {
                            if(data=="0"){
                                alert("添加失败");
                            }

                        }

                    });
                },
                delete:function (ids) {
                    for(var index in ids){
                        $.ajax({
                            type: "POST",
                            url: "code/delete.do",
                            async:false,
                            data: {"id":ids[index]},
                            success: function (data) {

                            }

                        });
                    }

                },
                getAll:function () {
                    var result=[];
                    $.ajax({
                        type: "POST",
                        url: "code/listAll.do",
                        async:false,
                        success: function (data) {
                            result=data;
                        }

                    });
                    return result;
                },
                getListByTerminal:function (terminalId) {
                    var result=[];
                    $.ajax({
                        type: "POST",
                        url: "code/listByTerminal.do",
                        data:{"id":terminalId},
                        async:false,
                        success: function (data) {
                            result=data;
                        }

                    });
                    return result;                
                },
                search:function (searchText) {
                    var result=[];
                    $.ajax({
                        type: "POST",
                        url: "code/search.do",
                        data:{"searchText":searchText},
                        async:false,
                        success: function (data) {
                            result=data;
                        }

                    });
                    return result;
                }
            },
            view:{
                hiddenModal:function () {
                    $('#codeModal').modal('toggle');
                },
                renderTable:function (data) {
                    var html="";
                    for(var index in data){
                        var codeData=data[index];
                        html+='<tr id="'+codeData.id+'">'+
                                '<th scope="row">'+(parseInt(index)+1)+'</th>'+
                                '<td><input type="checkbox" name="codeBox" value="'+codeData.id+'"/> </td>'+
                                '<td>'+codeData.code+'</td>'+
                                '<td>'+codeData.description+'</td>'+
                                '<td>'+codeData.terminal.name+'</td>'+
                                '<td>'+(codeData.remarks==undefined?"":codeData.remarks)+'</td>'+
                                '<td>'+codeData.terminal.responsibleName+'</td>'+
                                '<td><input type="hidden" value="'+codeData.terminal.id+'"/></td>'
                              '</tr>';
                    }

                    $('#code-tbody').html(html);

                },
                renderForm:function (data) {
                    $('#code-id').val(data.id);

                    $('#code-name').val(data.code);
                    $('#code-desc').val(data.description);
                    $('#code-remarks').val(data.remarks);
                    $('#code-form-terminal').val(data.terminalId);

                },
                getDataByIdFromView:function (id) {
                    var data={};
                    var tds=$('#'+id).find("td");

                    data.id=id;
                    data.code=$(tds[1]).text();
                    data.description=$(tds[2]).text();
                    data.remarks=$(tds[4]).text();
                    console.info($(tds[6]).find("input"))
                    data.terminalId=$(tds[6]).find("input")[0].value;
                    return data;
                },
                reset:function () {
                    $('#code-id').val("");
                    $('#code-form')[0].reset();
                },
                getSelected:function () {
                    var result=[];
                    $("[name = codeBox]:checkbox").each(function (index) {

                        if($(this).is(':checked')){
                            result.push($(this).val());
                        }
                    })

                    return result;
                },
                setModalTitle:function (title) {
                    $('#codeModal').find('.modal-title').text(title);
                },
                show:function () {
                    $('#code').css("display","block");
                    $('#terminal').css("display","none");
                },
                //渲染模块的select
                renderTerminal:function (data) {
                    var html="";
                    var defaultOption='<option value="0">全部</option>';
                    for(var index in data){
                        var terminal=data[index];
                        html+='<option value="'+terminal.id+'">'+terminal.name+'</option>';

                    }

                    $('#code-type-terminal').html(defaultOption+html);
                    $('#code-form-terminal').html(html);

                }
            }
        }


        //错误码管理相关代码 end

        codeManager.buss.load();

    })	
	
	
}(window,document,jQuery);

