import React, {useEffect, useState} from 'react';
import './CSS Styling/App.css';
import DevelopersForm from './Components/DevelopersForm';
import GalleryBoard from './Components/GalleryBoard';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>TEAM-SALT</h1>
        <body className="appBody">
        <div className="appSection">
        <DevelopersForm/>
        </div>
        <div className="appSection">
        <GalleryBoard/>
        </div>
        </body>
      </header>
    </div>
  );
}

export default App;
