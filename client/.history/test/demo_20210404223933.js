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
  
// var promise = new Promise(
//     function (resolve, reject){
//         reject('Fail!');
//     }
// );

// promise
//     .then(function(resolve){
//         console.log(resolve);
//     })
//     .catch(
//         function(err){
//             console.log(err);
//         }
//     )
var promise1 = new Promise(
    function(resolve){
        setTimeout(function(){
            resolve([1]);
        }, 2000);
    }
);
var promise2 = new Promise(
    function(resolve){
        setTimeout(function(){
            resolve([2,3]);
        }, 5000);
    }
);
Promise.all([promise1,promise2])
    .then(function(result){
        console.log(result);
    })