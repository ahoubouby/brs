import React, { memo } from "react";
const style = {
  margin: "3O px",
};
export default memo(({ text = "Loading" }) => {
  return (
    <div className="ui segment" style={style}>
      <div className="ui active dimmer">
        <div className="ui text loader">{text}</div>
      </div>
      <p></p>
    </div>
  );
});
