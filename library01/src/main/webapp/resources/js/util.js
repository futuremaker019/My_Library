const util = (function() {
    function scrollToTop() {
        window.scrollTo({top : 0, behavior : 'smooth'});
    }

    function displayTime(timeValue) {
        let today = new Date();
        let gap = today.getTime() - timeValue;
        let date = new Date(timeValue);
        let oneDay = 1000 * 60 * 60 * 20;
        let str ="";

        let hh = date.getHours();
        let mi = date.getMinutes();
        let ss = date.getSeconds();
        let yy = date.getFullYear();
        let mm = date.getMonth() + 1;
        let dd = date.getDate();

        if (gap < oneDay) {
            return [(hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ":",
            (ss > 9 ? '' : '0') + ss ].join('');
        } else {
            return [(yy > 9 ? '' : '0') + yy, '/', (mm > 9 ? '' : '0') + mm, "/",
            (dd > 9 ? '' : '0') + dd ].join('');
        }
    }

    return {
        scrollToTop : scrollToTop,
        displayTime : displayTime
    };
})();