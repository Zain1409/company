var URL = "http://localhost:8080/api/company/";
const getCompanyById = async (id) => {
    let res = await axios.get(BASE_URL + '/' + id)
        .catch(function (error) {
            if (error.response) {
                console.log(error.response.data.code);
                console.log(error.response.data.message);
                console.log(error.response.data.details);
            } else if (error.request) {
                console.log(error.request);
            } else {
                console.log('Error', error.message);
            }
        });
    return res.data;
}
var main = async () => {
    let id = localStorage.getItem('id');
    let data = await getCompanyById(id);
    console.log(data);

    document.getElementById('code').value = data.code;
    document.getElementById('name').value = data.name;
    document.getElementById('address').value = data.address;
    document.getElementById('email').value = data.email;
    document.getElementById('phone').value = data.phoneNumber;
    document.getElementById('website').value = data.website;
    document.getElementById('status').value = data.status;

}
main();