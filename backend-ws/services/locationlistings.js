const db = require('./db');
const helper = require('../helper');
const config = require('../config');

function findLocationbyCenter(lat, lang) {
    const query = { "location": { $near: { $geometry: { type: "Point", coordinates: [-121.89, 37.2] }, $minDistance: 10000, $maxDistance: 50000 } } };

    return new Promise((resolve, reject) => {
        db.client.db("sample_geospatial").collection("propertylistings").find(query).toArray(function (err, result) {
            if (err) throw reject(err);
            resolve(result);

        });
    })

}
module.exports = {
    findLocationbyCenter
}
