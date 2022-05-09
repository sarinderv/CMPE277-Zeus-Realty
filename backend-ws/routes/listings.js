const express = require('express');
const router = express.Router();
const listings = require('../services/listings');

/* GET listings. */
router.get('/', async function(req, res, next) {
  try {
    res.json(await listings.getMultiple(req.query.page));
  } catch (err) {
    console.error(`Error while getting listings `, err.message);
    res.status(err.statusCode || 500).json({'message': err.message});
  }
});

/* POST listings */
router.post('/', async function(req, res, next) {
  try {
    res.json(await listings.create(req.body));
  } catch (err) {
    console.error(`Error while posting listings `, err.message);
    res.status(err.statusCode || 500).json({'message': err.message});
  }
});

module.exports = router;
