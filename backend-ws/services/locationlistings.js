const db = require('./db');
const helper = require('../helper');
const config = require('../config');

function findLocationbyCenter(lat, long) {
    //-121.89, 37.2
    const query = { "location": { $near: { $geometry: { type: "Point", coordinates: [long, lat] }, $minDistance: 10000, $maxDistance: 50000 } } };
    console.log(JSON.stringify(query));
    return new Promise((resolve, reject) => {
        db.client.db("sample_geospatial").collection("propertylistings").find(query).toArray(function (err, result) {
            if (err) throw reject(err);
            resolve(result);

        });
    })
}
function findLocationbyBounds(box) {
    //-121.89, 37.2
    //  loc: { $geoWithin: { $box:  [ [ 0, 0 ], [ 100, 100 ] ] } }
    //, 
    //, 
    //  box=[ [ -122.17444444788339, 37.20444446317234 ], [ -121.80245270136577, 37.50622517874518 ] ] ;
    
    let boxes = [[box[0], box[1]], [box[2], box[3]]];
    const query = { "location": { $geoWithin: { $box: boxes } } };

    return new Promise((resolve, reject) => {
        db.client.db("sample_geospatial").collection("propertylistings").find(query).toArray(function (err, result) {
            if (err) throw reject(err);
            resolve(result);

        });
    })
}
module.exports = {
    findLocationbyCenter,
    findLocationbyBounds
}
