
/**
 *
 * @authors Leif
 * @date 2016年9月2日 16:02:48
 * @version 0.0.1
 */

;
Api.getCommon('onlineLotterys', {
    t: new Date().getTime()
}, function(res) {
    var ssc = ['<dt>时时彩</dt>'],
        five = ['<dt>11选5</dt>'],
        happy = ['<dt>快乐彩</dt>'],
        others = ['<dt>其他游戏</dt>'];
    var sscfirst = [31, 44, 13, 12, 11, 4, 37],
        sscsecond = [6,1, 23, 29, 8],
        sscorders = [1, 29, 8, 37, 33, 40, 6, 41, 13, 23, 12, 44, 11, 45, 4, 34, 31, 36, 46,21];
    var replaceCn = function(str) {
        return str;
    }
    if (res && res.code == '1') {
        var res = res.result,
            alllottery = {};
        var otherTag = (typeof top_tagdict !== 'undefined' ? top_tagdict : {})
        for (i = 0; i < res.length; i++) {
            alllottery[res[i].id] = {
                id: res[i].id,
                cn: res[i].cn,
                code: res[i].code
            };
            if ((String(res[i].code).indexOf('MKG') != -1 || String(res[i].code).indexOf('SSC') != -1) && String(res[i].code).indexOf('11Y') == -1) {
                //ssc.push('<dd><a href="/hz/mkg/bet/'+res[i].id+'.html" rel="'+res[i].code+'">'+replaceCn(res[i].cn)+'</a>'+ (typeof otherTag[res[i].code] !== 'undefined' ? otherTag[res[i].code] : '')+'</dd>');
            }
            if (String(res[i].code).indexOf('11Y') != -1) {
                five.push('<dd><a href="/hz/mkg/bet/' + res[i].id + '.html" rel="' + res[i].code + '">' + replaceCn(res[i].cn) + '</a>' + (typeof otherTag[res[i].code] !== 'undefined' ? otherTag[res[i].code] : '') + '</dd>');
            }
            if (String(res[i].code).indexOf('K3') != -1) {
                happy.push('<dd><a href="/hz/mkg/bet/' + res[i].id + '.html" rel="' + res[i].code + '">' + replaceCn(res[i].cn) + '</a>' + (typeof otherTag[res[i].code] !== 'undefined' ? otherTag[res[i].code] : '') + '</dd>');
            }
            if ((String(res[i].code).indexOf('K3') == -1 && String(res[i].code).indexOf('11Y') == -1 && String(res[i].code).indexOf('MKG') == -1 && String(res[i].code).indexOf('SSC') == -1)) {
                others.push('<dd><a href="/hz/mkg/bet/' + res[i].id + '.html" rel="' + res[i].code + '">' + replaceCn(res[i].cn) + '</a>' + (typeof otherTag[res[i].code] !== 'undefined' ? otherTag[res[i].code] : '') + '</dd>');
            }
        }
        for (i = 0; i < sscorders.length; i++) {
            if(typeof alllottery[sscorders[i]] !== 'undefined'){
                ssc.push('<dd><a href="/hz/mkg/bet/' + alllottery[sscorders[i]].id + '.html" rel="' + alllottery[sscorders[i]].code + '">' + replaceCn(alllottery[sscorders[i]].cn) + '</a>' + (typeof otherTag[alllottery[sscorders[i]].code] !== 'undefined' ? otherTag[alllottery[sscorders[i]].code] : '') + '</dd>');
            }
        }
        ssc.push('<dd><a href="/hz/mkg/bet/39.html" >北京5分彩</a></dd>');
        happy.push('<dd><a href="/hz/mkg/bet/39.html" >北京快乐8</a></dd>');
        $('.menu-list > dl.ssc').html(ssc.join(''));
        $('.menu-list > dl.five').html(five.join(''));
        $('.menu-list > dl.happy').html(happy.join(''));
        $('.menu-list > dl.others').html(others.join(''));
    }
});