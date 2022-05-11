const express = require('express');
const router = express.Router();
const locationlistings = require('../services/locationlistings');

/* GET locationlistings. */
router.get('/point', async function(req, res, next) {
  try {
    let query=req.query;
    console.log(query.lat);
    console.log(query.long);
    res.json(await locationlistings.findLocationbyCenter(parseFloat(query.lat),parseFloat(query.long)));
  } catch (err) {
    console.error(`Error while getting listings `, err.message);
    res.status(err.statusCode || 500).json({'message': err.message});
  }
});
router.get('/bounds', async function(req, res, next) {
  try {
    let bounds=req.query.bounds;
    bounds=bounds.split(',');
    bounds=bounds.map(e=>parseFloat(e));
    console.log(bounds);
    res.json(await locationlistings.findLocationbyBounds(bounds));
  } catch (err) {
    console.error(`Error while getting listings `, err.message);
    res.status(err.statusCode || 500).json({'message': err.message});
  }
});
router.get('/byproperty/:id', async function(req, res, next) {
  try {
    let id=req.params.id;
    res.json(await locationlistings.findPropertyById(id));
  } catch (err) {
    console.error(`Error while getting listings `, err.message);
    res.status(err.statusCode || 500).json({'message': err.message});
  }
});
router.get('/all', async function(req, res, next) {
  try {
    res.json(await locationlistings.fetchall());
  } catch (err) {
    console.error(`Error while getting listings `, err.message);
    res.status(err.statusCode || 500).json({'message': err.message});
  }
});

module.exports = router;
