// setTimeout(function(){
//     console.log(1);
//     setTimeout(function(){
//       console.log(2);
//       setTimeout(function(){
//         console.log(3);
//         setTimeout(function(){
//           console.log(4);
//           setTimeout(function(){
//             console.log(5);
//           },1000);
//         },1000);
//       },1000);
//     },1000);
//   },1000);
  
var promise = new Promise(
    function (resolve, reject){
        resolve('Success!');
    }
);

promise
    .then(function(resolve){
        console.log(resolve);
    })