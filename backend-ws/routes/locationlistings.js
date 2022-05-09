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
    let query=req.query;
    console.log(query.lat);
    console.log(query.long);
    res.json(await locationlistings.findLocationbyCenter(parseFloat(query.lat),parseFloat(query.long)));
  } catch (err) {
    console.error(`Error while getting listings `, err.message);
    res.status(err.statusCode || 500).json({'message': err.message});
  }
});


module.exports = router;
