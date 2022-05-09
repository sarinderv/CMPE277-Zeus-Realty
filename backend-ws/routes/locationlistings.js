const express = require('express');
const router = express.Router();
const locationlistings = require('../services/locationlistings');

/* GET locationlistings. */
router.get('/', async function(req, res, next) {
  try {
    res.json(await locationlistings.findLocationbyCenter(1,1));
  } catch (err) {
    console.error(`Error while getting listings `, err.message);
    res.status(err.statusCode || 500).json({'message': err.message});
  }
});



module.exports = router;
