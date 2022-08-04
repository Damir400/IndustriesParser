function yahooParser() {
    let result = [];
    result.push({
        'name': 'United Parcel Service, Inc.',
        'urlYahoo':'https://finance.yahoo.com//quote/UPS/profile?p=UPS',
    'description': document.querySelector('#Col1-0-Profile-Proxy > section > section.quote-sub-section.Mt\\(30px\\) > p').textContent,
        'corporateGovernance': document.querySelector('#Col1-0-Profile-Proxy > section > section.Mt\\(30px\\).corporate-governance-container > div').textContent
});

    return result;

}

function getList(){
    let result = [];
    for (let i = 1; i <= 50; i++) {
        result.push({
            'name': document.querySelector("#scr-res-table > div.Ovx\\(a\\).Ovx\\(h\\)--print.Ovy\\(h\\).W\\(100\\%\\) > table > tbody > tr:nth-child("+ i +")").children[1].textContent,
            'urlYahoo': 'https://finance.yahoo.com/'+document.querySelector("#scr-res-table > div.Ovx\\(a\\).Ovx\\(h\\)--print.Ovy\\(h\\).W\\(100\\%\\) > table > tbody > tr:nth-child(1)> td> a").getAttribute('href').split('?').join('/profile?'),

    });
    }
    return result;
}
function getIndeedPage(){
    document.querySelector('#ifl-InputFormField-3').value=name;
    document.querySelector('#main > div > div.css-19t9h4o.eu4oa1w0 > form > div > div.css-1fv2pee.eu4oa1w0 > button').click();
    document.querySelector('#main > div > div.css-evzha8.eu4oa1w0 > section > div > div > div > div >a').click();
}

function getIndeedInfo() {
    let result = {
        'ceo':'',
        'founded':'',
        'companySize':'',
        'revenue':'',
        'industry':'',
        'headquarters':'',
        'link':''

    };
    let item = document.querySelector("#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main > div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div > ul > li:nth-child(1)");
    if (item && item.outerText.startsWith('CEO')) {
        result.ceo = item.outerText.split('\n')[1];
    }

    item = document.querySelector("#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main > div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div > ul > li:nth-child(2)");
    if (item && item.outerText.startsWith('Founded')) {
        result.founded = item.outerText.split('\n')[1];
    }

    item = document.querySelector("#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main > div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div > ul > li:nth-child(3)");
    if (item && item.outerText.startsWith('Company size')) {
        result.companySize = item.outerText.split('\n')[1] +
                " " + item.outerText.split('\n')[2];
    }

    item = document.querySelector("#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main > div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div > ul > li:nth-child(4)");
    if (item && item.outerText.startsWith('Revenue')) {
        result.revenue = item.outerText.split('\n')[1] +
                 " " + item.outerText.split('\n')[2];
    }

    item = document.querySelector("#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main > div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div > ul > li:nth-child(5)");
    if (item && item.outerText.startsWith('Industry')) {
        result.industry = item.outerText.split('\n')[1];
    }

    item = document.querySelector("#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main > div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div > ul > li:nth-child(6)");
    if (item && item.outerText.startsWith('Headquarters')) {
        result.headquarters = item.outerText.split('\n')[1];
    }

    item = document.querySelector("#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main > div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div > ul > li:nth-child(7)");
    if (item && item.outerText.startsWith('Link')) {
        result.link = item.outerText.split('\n')[1];
    }
    return JSON.stringify(result);
}


//document.querySelector("#ifl-InputFormField-3").value="Taco Bell" поиск
//document.querySelector("#main > div > div.css-19t9h4o.eu4oa1w0 > form > div > div.css-1fv2pee.eu4oa1w0 > button").click() клик поиска
//document.querySelector("#main > div > div.css-evzha8.eu4oa1w0 > section > div > div > div > div >a").click() переход на нужную страницу
