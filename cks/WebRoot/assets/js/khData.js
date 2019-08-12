$(function () {
    var itemIndex = 0;   
    var tabLoadEndArray = [false, false, false];
    var tabLenghtArray = [28, 15, 47];
    var tabScroolTopArray = [0, 0, 0];
    
    // dropload
    var dropload = $('.khfxWarp').dropload({
        scrollArea: window,
        domDown: {
            domClass: 'dropload-down',
            domRefresh: '<div class="dropload-refresh">上拉加载更多</div>',
            domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
            domNoData: '<div class="dropload-noData">已无数据</div>'
        },
        loadDownFn: function (me) {
            setTimeout(function () {
                if (tabLoadEndArray[itemIndex]) {
                    me.resetload();
                    me.lock();
                    me.noData();
                    me.resetload();
                    return;
                }
                var result = '';
                for (var index = 0; index < 10; index++) {
                    if (tabLenghtArray[itemIndex] > 0) {
                        tabLenghtArray[itemIndex]--;
                    } else {
                        tabLoadEndArray[itemIndex] = true;
                        break;
                    }
                    if (itemIndex == 0) {
                        result
                        += ''
                        + '    <hgroup class="khfxRow">'
                        + '      <header><span> 安装地点：AAA</span>2015-10-18  23:32:13 &nbsp 报修人<header>'
                        + '      <div class="mid">'
                        + '        <img class="photo" src="assets/images/shebei.png" style="width: 30px; height: 30px">'
                        + '        <span><label>编号：</label>弦上漫步</span> '
                        + '        <span><label>工单号：</label>' + tabLenghtArray[itemIndex] + '</span> '
                        + '        <span><label>名称：</label>1391****746</span> '
                        + '        <span><label>故障描述：</label>中信证券</span> '
                        + '      </div>'
                        + '    </hgroup>';
                    } else if (itemIndex == 1) {
                        result
                        += ''
                        + '    <hgroup class="khfxRow">'
                        + '      <header><span>设备类型</span>2015-10-18  23:32:13</header>'
                        + '      <div class="mid">'
                        + '        <img class="photo" src="${proPath}/assets/images/shebei.png" >'
                        + '        <span><label>昵称：</label>弦上漫步</span> '
                        + '        <span><label>账号：</label>' + tabLenghtArray[itemIndex] + '</span> '
                        + '        <span><label>手机：</label>1391****746</span> '
                        + '        <span><label>券商：</label>中信证券</span> '
                        + '      </div>'
                        + '    </hgroup>';
                    } else if (itemIndex == 2) {
                        result
                        += ''
                        + '<hgroup class="khfxRow">'
                        + '<header><span>已开户成功</span>2015-10-18  23:32:13</header>'
                        + '<div class="mid">'
                        + '<img class="photo" src="${proPath}/assets/images/shebei.png" >'
                        + '<span><label>昵称：</label>弦上漫步</span> '
                        + '<span><label>账号：</label>' + tabLenghtArray[itemIndex] + '</span> '
                        + '<span><label>手机：</label>1391****746</span> '
                        + '<span><label>券商：</label>中信证券</span> '
                        + '</div>'
                        + '</hgroup>';
                    }
                }
                $('.khfxPane').eq(itemIndex).append(result);
                me.resetload();
            }, 500);
        }
    });


    $('.tabHead span').on('click', function () {

        tabScroolTopArray[itemIndex] = $(window).scrollTop();
        var $this = $(this);
        itemIndex = $this.index();
        $(window).scrollTop(tabScroolTopArray[itemIndex]);
        
        $(this).addClass('active').siblings('.tabHead span').removeClass('active');
        $('.tabHead .border').css('left', $(this).offset().left + 'px');
        $('.khfxPane').eq(itemIndex).show().siblings('.khfxPane').hide();

        if (!tabLoadEndArray[itemIndex]) {
            dropload.unlock();
            dropload.noData(false);
        } else {
            dropload.lock('down');
            dropload.noData();
        }
        dropload.resetload();
    });
});