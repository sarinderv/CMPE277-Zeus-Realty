const { Pool } = require('pg');
const config = require('../config');
const pool = new Pool(config.db);

const { MongoClient, ServerApiVersion } = require('mongodb');
const uri = config.mongodb.DB_URI;
console.log('uri', uri);
const client = new MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true, serverApi: ServerApiVersion.v1 });
client.connect(err => {
  if (err) throw err;
  // perform actions on the collection object
  //const collection = client.db("sample_airbnb").collection("listingsAndReviews");
  //const query = { property_type: "House" };

  const collection = client.db("sample_geospatial").collection("listingsAndReviews");
  const query = { "location": { $near: { $geometry: { type: "Point", coordinates: [-120, 35] }, $minDistance: 10000, $maxDistance: 50000 } } };
  console.log(query);

  //const collection = client.db("sample_geospatial").collection("shipwrecks");
  //const query = { location: { $nearSphere: { $geometry: { type: "Point", coordinates: [ -73.93414657, 40.82302903 ] }, $maxDistance: 5 } } };
  //const query = { location: { $geoWithin: { $centerSphere: [ [ -73.93414657, 40.82302903 ], 5 / 3963.2 ] } } };

  // const collection = client.db("sample_geospatial").collection("shipwrecks");
  // const query = { location: { $geoWithin: { $center: [ [ -73.54, 45.54 ], 160000 ] } } };
  collection.find(query).toArray(function (err, result) {
    if (err) throw err;
    console.log(result);
    client.close();
  });
});

/**
 * Query the database using the pool
 * @param {*} query 
 * @param {*} params 
 * 
 * @see https://node-postgres.com/features/pooling#single-query
 */
async function query(query, params) {
    const {rows, fields} = await pool.query(query, params);

    return rows;
}

module.exports = {
  query
}
