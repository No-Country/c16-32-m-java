import React ,{ useState }from 'react';
import './Slider.css';

const Slider = ({ onChange }) => {

  const [isChecked, setIsChecked] = useState(false);

  const SliderChange = (e) => {
    const checked = e.target.checked;
    setIsChecked(checked);
    onChange(checked);
  };

  return (
    <label className="switch">
      <input
        id="toggle-theme"
        type="checkbox"
        checked = {isChecked}
        onChange={SliderChange}
      />
      <span className="slider"></span>
    </label>
  );
};

export default Slider;

